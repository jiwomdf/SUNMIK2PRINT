package id.anta.sunmik1jiwoprint

import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.widget.Toast
import com.sunmi.extprinterservice.ExtPrinterService


class MainActivity : AppCompatActivity() {

    lateinit var ext //k1 打印服务
            : ExtPrinterService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        boundService()
    }

    private fun boundService() {

        val serviceConnection = object: ServiceConnection {

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {

                try {
                    ext = ExtPrinterService.Stub.asInterface(service)

                    ext.printerInit()
                    ext.printText("123456\n" +
                            "123456\n" +
                            "123456\n" +
                            "123456\n" +
                            "123456\n" +
                            "123456\n" +
                            "123456\n" +
                            "123456\n" +
                            "123456\n" +
                            "123456\n" +
                            "123456\n" +
                            "123456\n" +
                            "123456\n" +
                            "123456\n" +
                            "123456\n" +
                            "123456\n" +
                            "123456\n" +
                            "123456\n" +
                            "123456\n" +
                            "123456\n")
                    Toast.makeText(this@MainActivity, "Printing", Toast.LENGTH_SHORT).show()

                    ext.cutPaper(0, 0)
                } catch (ex: Exception){
                    Toast.makeText(this@MainActivity, ex.message.toString(), Toast.LENGTH_SHORT).show()
                }

            }

            override fun onServiceDisconnected(p0: ComponentName?) {
                Toast.makeText(this@MainActivity, "Disconected", Toast.LENGTH_SHORT).show()
                unbindService(this)
            }
        }

        val intent = Intent()
        intent.setPackage("com.sunmi.extprinterservice")
        intent.action = "com.sunmi.extprinterservice.PrinterService"
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)


    }
}