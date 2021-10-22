package org.lucashos.core.extension

import java.text.SimpleDateFormat
import java.util.Locale

fun String.toDateFormat(): String? =
    this
        .takeIf { this.isNotEmpty() }
        ?.let {
            val originalFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val targetFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            return originalFormat.parse(this)?.let {
                targetFormat.format(it)
            }.toString()
        }

fun String?.orElse(default: String): CharSequence = this ?: default