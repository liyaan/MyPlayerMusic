package com.liyaan.myplayermusic

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
//ffplay -ar 44100 -ac 2 -f s16le output.pcm
class MainActivity : AppCompatActivity() {
    private var playerMusic:PlayerMusic? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playerMusic = PlayerMusic()
        findViewById<TextView>(R.id.sample_text).text = stringFromJNI()
        PermissionUtils.isGrantExternalRW(this,0)
    }
    private external fun stringFromJNI(): String
    fun clickToPCM(view: View) {
        val input: String =
            File(Environment.getExternalStorageDirectory(), "input.mp3").absolutePath
        val output: File =
            File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path, "output.pcm")
//        if (!output.exists()){
//            output.createNewFile()
//        }
        if (!output.exists()) {
            val mkdirs = output.createNewFile()
            Log.i("TAG",output.absolutePath)
            if (!mkdirs) {
                Log.e("TAG", "文件夹创建失败");
            } else {
                Log.e("TAG", "文件夹创建成功");
            }
        }
        playerMusic?.nativePlayer(input,output.absolutePath)
    }


}