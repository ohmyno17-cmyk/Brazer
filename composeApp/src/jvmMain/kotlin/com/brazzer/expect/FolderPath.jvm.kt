package com.brazzer.expect

actual fun getDownloadFolderPath(): String = System.getProperty("user.home") + "/Downloads"