package good.damn.clientsocket.services

abstract class BaseService<DELEGATE> {
    var delegate: DELEGATE? = null
    abstract fun start()
}