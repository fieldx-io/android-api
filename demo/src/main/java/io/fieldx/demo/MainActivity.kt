package io.fieldx.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import io.fieldx.api.FieldXAPI

class MainActivity : AppCompatActivity() {
    lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById(R.id.text)
    }

    fun getDeviceDetails(view: View) {
        val device = FieldXAPI.getDeviceDetails(this, true);
        if (device == null) {
            showAPIError()
            return
        }

        text.text = "Device ID: ${device.deviceId}\n" +
                "IMEI: ${device.imeiNumber}\n" +
                "SN: ${device.serialNumber}\n" +
                "For more device details, check FieldXDevice object returned by API"

    }

    private fun showAPIError() {
        text.text =
            "\nAPI Call Failed.\nMake sure your app is whitelisted by Admin under Global Config"
    }

    fun uninstallApp(view: View) {
        val pkg = findViewById<TextView>(R.id.app_name);
        if (pkg.text.isBlank()) {
            Toast.makeText(this, "Enter a package name", Toast.LENGTH_SHORT).show()
            return
        }

        if (!FieldXAPI.uninstallApp(this, pkg.text.toString().trim())) {
            showAPIError()
        }
    }

    fun hideApp(view: View) {
        val pkg = findViewById<TextView>(R.id.app_name);
        if (pkg.text.isBlank()) {
            Toast.makeText(this, "Enter a package name", Toast.LENGTH_SHORT).show()
            return
        }

        if (!FieldXAPI.hideApp(this, pkg.text.toString().trim())) {
            showAPIError()
        }
    }

    fun showApp(view: View) {
        val pkg = findViewById<TextView>(R.id.app_name);
        if (pkg.text.isBlank()) {
            Toast.makeText(this, "Enter a package name", Toast.LENGTH_SHORT).show()
            return
        }

        if (!FieldXAPI.showApp(this, pkg.text.toString().trim())) {
            showAPIError()
        }
    }
}