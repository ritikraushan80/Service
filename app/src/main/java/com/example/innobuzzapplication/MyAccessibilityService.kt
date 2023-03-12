package com.example.innobuzzapplication

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast


class MyAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (event.packageName?.toString() == "com.whatsapp" && event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            Toast.makeText(this, "WhatsApp Launched.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onInterrupt() {
    }
}