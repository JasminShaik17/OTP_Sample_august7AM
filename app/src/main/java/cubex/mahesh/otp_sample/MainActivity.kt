package cubex.mahesh.otp_sample

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL
import java.util.*

class MainActivity : AppCompatActivity() {
    var otp_num:Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)



        b1.setOnClickListener {
            if(et3.text.length==10){

                getOtpNum( )

                    var task = MyTask(et3,otp_num!!)
                    task.execute()

                et4.visibility = EditText.VISIBLE
                reg.visibility = Button.VISIBLE
                reg.setOnClickListener {
                    if(otp_num == et4.text.toString().toInt()){
                        Toast.makeText(this@MainActivity,
                                "Thanks for Registration",
                                Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this@MainActivity,
                                "Please give correct OTP...",
                                Toast.LENGTH_LONG).show()
                    }
                }

            }else{

            }
        }
    }

    fun getOtpNum() {
        var rnd = Random( )
        otp_num = rnd.nextInt(999999-100000)+100000
    }

    class  MyTask(var et3:EditText,var otp_num:Int) :AsyncTask<Unit,Unit,Unit>( )
    {
        override fun doInBackground(vararg p0: Unit?) {
            var u = URL("http://smsc.biz/httpapi/send?username=mahesh.choutkuri@gmail.com&password=Boppi1108&sender_id=SMSIND&route=T&phonenumber=${et3.text.toString()}&message=$otp_num")
            u.openStream()
        }

    }
}
