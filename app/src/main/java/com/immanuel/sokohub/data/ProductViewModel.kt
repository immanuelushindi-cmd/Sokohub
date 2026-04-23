package com.immanuel.sokohub.data

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.immanuel.sokohub.models.Product                  // ✔ matches renamed data class
import com.immanuel.sokohub.navigation.ROUTE_VIEW_PRODUCTS
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.InputStream

class ProductViewModel : ViewModel() {

    companion object {
        private const val CLOUDINARY_URL = "https://api.cloudinary.com/v1_1/ds8y1vfji/image/upload"
        private const val UPLOAD_PRESET = "sokohub"

        // Singleton OkHttpClient — reuse across all requests
        private val httpClient = OkHttpClient()
    }

    private val _products = mutableStateListOf<Product>()
    val products: List<Product> = _products

    // -------------------------------------------------------------------------
    // Upload Product
    // -------------------------------------------------------------------------

    fun uploadProduct(
        imageUri: Uri?,
        name: String,
        category: String,
        brand: String,
        price: String,
        description: String,
        context: Context,
        navController: NavController
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val imageUrl = imageUri?.let { uploadToCloudinary(context, it) }

                val ref = FirebaseDatabase.getInstance().getReference("Products").push()
                val productData = mapOf(
                    "id"          to ref.key,
                    "name"        to name,
                    "category"    to category,
                    "brand"       to brand,
                    "price"       to price,
                    "description" to description,
                    "imageUrl"    to imageUrl
                )
                ref.setValue(productData).await()

                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Product saved successfully", Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_VIEW_PRODUCTS)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Product not saved: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // -------------------------------------------------------------------------
    // Fetch Products
    // -------------------------------------------------------------------------

    fun fetchProducts(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val snapshot = FirebaseDatabase.getInstance()
                    .getReference("Products")
                    .get()
                    .await()

                val fetched = mutableListOf<Product>()
                for (child in snapshot.children) {
                    val product = child.getValue(Product::class.java)
                    product?.let {
                        it.id = child.key
                        fetched.add(it)
                    }
                }

                withContext(Dispatchers.Main) {
                    _products.clear()
                    _products.addAll(fetched)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Failed to load products: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // -------------------------------------------------------------------------
    // Delete Product
    // -------------------------------------------------------------------------

    fun deleteProduct(productId: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                FirebaseDatabase.getInstance()
                    .getReference("Products")
                    .child(productId)
                    .removeValue()
                    .await()

                withContext(Dispatchers.Main) {
                    _products.removeAll { it.id == productId }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Product not deleted: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // -------------------------------------------------------------------------
    // Update Product
    // -------------------------------------------------------------------------

    fun updateProduct(
        productId: String,
        imageUri: Uri?,            // null → keep existing image URL
        existingImageUrl: String?, // pass the current URL from the product
        name: String,
        category: String,
        brand: String,
        price: String,
        description: String,
        context: Context,
        navController: NavController
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val imageUrl = if (imageUri != null) {
                    uploadToCloudinary(context, imageUri)
                } else {
                    existingImageUrl
                }

                val updatedProduct = mapOf(
                    "id"          to productId,
                    "name"        to name,
                    "category"    to category,
                    "brand"       to brand,
                    "price"       to price,
                    "description" to description,
                    "imageUrl"    to imageUrl
                )

                FirebaseDatabase.getInstance()
                    .getReference("Products")
                    .child(productId)
                    .setValue(updatedProduct)
                    .await()

                withContext(Dispatchers.Main) {
                    fetchProducts(context)
                    Toast.makeText(context, "Product updated successfully", Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_VIEW_PRODUCTS)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Update failed: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    // -------------------------------------------------------------------------
    // Cloudinary Upload Helper
    // -------------------------------------------------------------------------

    private fun uploadToCloudinary(context: Context, uri: Uri): String {
        val inputStream: InputStream = context.contentResolver.openInputStream(uri)
            ?: throw Exception("Cannot open image stream")

        val fileBytes = inputStream.use { it.readBytes() }

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "file", "image.jpg",
                RequestBody.create("image/*".toMediaTypeOrNull(), fileBytes)
            )
            .addFormDataPart("upload_preset", UPLOAD_PRESET)
            .build()

        val request = Request.Builder()
            .url(CLOUDINARY_URL)
            .post(requestBody)
            .build()

        val responseBody = httpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw Exception("Cloudinary upload failed: ${response.code}")
            response.body?.string() ?: throw Exception("Empty response from Cloudinary")
        }

        return JSONObject(responseBody).getString("secure_url")
    }
}