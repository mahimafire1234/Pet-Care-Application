package com.mahima.animestreamingapp.ui.myprofile

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.*
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.mahima.animestreamingapp.R
import com.mahima.animestreamingapp.api.ServiceBuilder
import com.mahima.animestreamingapp.repository.UserRepository
import com.mahima.animestreamingapp.ui.AboutusFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class UpdateUserInfoActivity : AppCompatActivity() {
//    declaring variables
    private lateinit var etusernameupdate : EditText
    private lateinit var etemailupdate : EditText
    private lateinit var etpasswordupdate : EditText
    private lateinit var etconfirmpasswordupdate : EditText
    private lateinit var btnupdateuserinfo : Button
    private lateinit var imgviewphoto : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user_info)

//        initializing
        etusernameupdate = findViewById(R.id.etusernameupdate)
        etemailupdate = findViewById(R.id.etemailupdate)
        etpasswordupdate = findViewById(R.id.etpasswordupdate)
        etconfirmpasswordupdate =findViewById(R.id.etconfirmpasswordupdate)
        btnupdateuserinfo = findViewById(R.id.btnupdateuserinfo)
        imgviewphoto = findViewById(R.id.imgviewphoto)

//        get intent
        val email = intent.getStringExtra("email")
        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")
        val photo = intent.getStringExtra("picture")
        //image
        if(photo == null){
            Glide.with(this)
                .load("data:image/png;base64,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")
                .into(imgviewphoto)
        }else{
            Glide.with(this)
                .load("http://192.168.1.80:80/"+photo)
                .into(imgviewphoto)
        }

//        set variables
        etusernameupdate.setText(username)
        etemailupdate.setText(email)

        btnupdateuserinfo.setOnClickListener { update() }

//        image click
        imgviewphoto.setOnClickListener { openMenu() }

    }

//    open pop up menu
    private fun openMenu(){
        val popMenu = PopupMenu(this,imgviewphoto)
        popMenu.menuInflater.inflate(R.menu.forimage,popMenu.menu)

        popMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menucamera -> openCamera()
                    R.id.menugallery -> openGallery()
                }
                true
        }
        popMenu.show()
    }

    //    check permission
    private val permissions = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    private fun checkPermission():Boolean{
        var haspermission =true

        for(permission in permissions){
            if(ActivityCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
                haspermission = false
                break
            }
        }
        return haspermission
    }

//    request codes
    private val CAMERA_CODE = 1
    private val GALLERY_CODE = 0

    private var imageUrl =""

//    camera
    private fun openCamera(){
        if(!checkPermission()){
            ActivityCompat.requestPermissions(this,permissions,1)
        }else{
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent,CAMERA_CODE)
        }

    }

//    gallery
    private fun openGallery(){
        if(!checkPermission()){
            ActivityCompat.requestPermissions(this,permissions,0)
        }else{
            val galleryIntent = Intent(Intent.ACTION_PICK)
            galleryIntent.type="image/*"
            startActivityForResult(galleryIntent,GALLERY_CODE)
        }

    }

//    image upload
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
//            if for gallery
            if(requestCode == GALLERY_CODE && data != null){
                val selectedImage = data.data
//                Toast.makeText(this,"here",Toast.LENGTH_SHORT).show()
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val contentResolver = this.contentResolver
                val cursor =
                    contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                imageUrl = cursor.getString(columnIndex)
                imgviewphoto.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
                Log.d("imageurl", imageUrl)
                cursor.close()
            } else if (requestCode == CAMERA_CODE && data != null) {
                val imageBitmap = data.extras?.get("data") as Bitmap
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val file = bitmapToFile(imageBitmap, "$timeStamp.jpg")
                imageUrl = file!!.absolutePath
                imgviewphoto.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
            }

        }
    }

//    function for bitmap to file
    private fun bitmapToFile(
        bitmap: Bitmap,
        fileNameToSave: String
        ): File?
    {
        var file: File? = null
        return try {
            file = File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                .toString() + File.separator + fileNameToSave
            )
            file.createNewFile()

            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)
            val bitMapData = bos.toByteArray()
            val fos = FileOutputStream(file)
            fos.write(bitMapData)
            fos.flush()
            fos.close()
            file

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            file
        }
    }

    fun getMimeType(file: File): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(file.path)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        return type
    }

    private fun uploadImage() {

        val file = File(imageUrl)
        val mimeType = getMimeType(file)
        val reqFile = RequestBody.create(MediaType.parse(mimeType), file)
        val body = MultipartBody.Part.createFormData("file", file.name, reqFile)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepository = UserRepository()
                val response = userRepository.uploadImage(ServiceBuilder.userId!!, body)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@UpdateUserInfoActivity,
                            "Upload Image success",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Log.d("Error ", ex.localizedMessage)
                    Toast.makeText(
                        this@UpdateUserInfoActivity,
                        ex.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    //    update function
    private fun update(){
        val fullname = etusernameupdate.text.toString()
        val email = etemailupdate.text.toString()
        val updatepassword = etpasswordupdate.text.toString()
        val newpassword = etconfirmpasswordupdate.text.toString()

        if(etconfirmpasswordupdate.text.isEmpty()){
            etconfirmpasswordupdate.error = "Please type your new passsword"
            etconfirmpasswordupdate.requestFocus()
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = UserRepository()
                val response = repository.updateUser(ServiceBuilder.userId!!,fullName = fullname,email = email,password = newpassword)
                if(response.success == true){
                    withContext(Dispatchers.Main){
                        uploadImage()
                        Toast.makeText(this@UpdateUserInfoActivity,"Your user info has been updates successfully",Toast.LENGTH_LONG).show()
                    }
                }
            }
            catch (ex:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@UpdateUserInfoActivity,ex.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}