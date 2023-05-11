package com.example.accessibility

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setHeading(findViewById(R.id.heading1))
        setHeading(findViewById(R.id.heading2))

        val goToPageTextView = findViewById<TextView>(R.id.go_to_page)
        setRoleDescription(goToPageTextView, "Link")
        goToPageTextView.setOnClickListener {
            Toast.makeText(this, "Go to page clicked", Toast.LENGTH_SHORT).show()
        }

        val triswitchContainer = findViewById<FrameLayout>(R.id.triswitch_container)
        triswitchContainer.addView(TriSwitch(this))
    }

    private fun setHeading(textView: TextView) {
        ViewCompat.setAccessibilityHeading(textView, true)
    }

    private fun setRoleDescription(textView: TextView, roleDescription: String) {
        ViewCompat.setAccessibilityDelegate(textView, object : AccessibilityDelegateCompat() {
            override fun onInitializeAccessibilityNodeInfo(
                host: View,
                info: AccessibilityNodeInfoCompat
            ) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                info.roleDescription = roleDescription
            }
        })
    }
}