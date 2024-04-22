package good.damn.filesharing.listeners.network.service

interface HotspotServiceListener {

    fun onGetHotspotIP(
        addressList: String
    )

}