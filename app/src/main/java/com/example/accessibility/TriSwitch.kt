package com.example.accessibility;

import android.content.Context
import android.widget.Switch
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat

class TriSwitch(private val context: Context) : Switch(context) {
    // 0, 1, or 2
    var currentState: Int = 0
        private set

    init {
        updateAccessibilityActions()
    }

    private fun updateAccessibilityActions() {
        ViewCompat.replaceAccessibilityAction(
            this, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK,
            "change switch state"
        ) { view, args ->
            moveToNextState()
        }
    }

    private fun moveToNextState(): Boolean {
        isChecked = !isChecked
        currentState = (currentState + 1) % 3
        Toast.makeText(context, "Current State is: $currentState", Toast.LENGTH_SHORT).show()
        return true
    }
}
