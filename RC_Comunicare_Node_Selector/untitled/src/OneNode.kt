import java.net.*
import java.time.chrono.ThaiBuddhistChronology
import kotlin.random.Random

class OneNode (
    private val currentPort: Int,
    private val n2Port: Int,
    private val n3Port: Int
) {
    fun start() {
        Thread {
            val socket = DatagramSocket(currentPort)
            val buffer = ByteArray(1024)
            println("Sefu asteapta pentru ACK pe portul: $currentPort")
            while (true){
                val packet = DatagramPacket(buffer, buffer.size)
                socket.receive(packet)
                val ack = String(packet.data, 0, packet.length).trim()
                println("Sefu a primit $ack")
            }
        }.start()
        Thread {
            for (i in 1..100) {
                val targetPort = if(Random.nextBoolean()) n2Port else n3Port
                sendValue(i, targetPort)
                Thread.sleep(200)
            }
        }.start()
    }
    private fun sendValue(value: Int, port: Int) {
        val socket = DatagramSocket()
        val message = value.toString()
        val data = message.toByteArray()
        val packet = DatagramPacket(data, data.size, InetAddress.getByName("127.0.0.1"), port)
        socket.send(packet)
        println("Sefu a trimis $value catre $port")
        socket.close()
    }
}