fun main() {
    val sefu = OneNode(currentPort = 6000, n2Port = 6001, n3Port = 6002)
    val nod2 = SecondThird(currentPort = 6001, ackRez = { it % 3 == 0 }, ackPort = 6000)
    val nod3 = SecondThird(currentPort = 6002, ackRez = { it % 5 == 0 }, ackPort = 6000)
    sefu.start()
    nod2.start()
    nod3.start()
}