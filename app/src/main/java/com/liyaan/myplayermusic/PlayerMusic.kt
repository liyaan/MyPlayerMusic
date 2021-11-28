package com.liyaan.myplayermusic

class PlayerMusic {
    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
    external fun nativePlayer(input:String,output:String)
}