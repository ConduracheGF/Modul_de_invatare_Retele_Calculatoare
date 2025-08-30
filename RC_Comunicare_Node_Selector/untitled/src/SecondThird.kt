import java.net.*

class SecondThird (
    private val currentPort:Int,
    private val ackRez: (Int) -> Boolean,
    private val ackPort: Int
) {
    fun start() {
        Thread{
            val socket = DatagramSocket(currentPort)
            val buffer = ByteArray(1024)
            println("Ma supun lui $currentPort")
            while (true){
                val pachet = DatagramPacket(buffer,buffer.size)
                socket.receive(pachet)
                val message = String(pachet.data, 0, pachet.length).trim()
                val value = message.toInt()
                println("Nod $currentPort a primit valoarea: $value")
                if (ackRez(value))
                    sendAck(value)
            }
        }.start()
    }
    private fun sendAck(value: Int) {
        val socket = DatagramSocket()
        val message = "ACK for $value"
        val data = message.toByteArray()
        val packet = DatagramPacket(data, data.size, InetAddress.getByName("127.0.0.1"), ackPort)
        socket.send(packet)
        println("Nodul $currentPort a trimis $message to N1")
        socket.close()
    }
}