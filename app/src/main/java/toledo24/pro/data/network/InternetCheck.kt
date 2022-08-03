package toledo24.pro.data.network

import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

internal class InternetCheck {

        fun execute(): Boolean {
        return try {
            val sock = Socket()
            sock.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            sock.close()
            true
        } catch (e: IOException) {
            false
        }
        }

}