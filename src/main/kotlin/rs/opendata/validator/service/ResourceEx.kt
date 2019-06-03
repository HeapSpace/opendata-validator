package rs.opendata.validator.service

import rs.opendata.validator.model.Resource

data class ResourceEx(val resource: Resource) {
    private val downloader = Downloader()

    fun download(): Downloader.Data {
        return downloader.download(resource.url)
    }

    fun download(url: String): Downloader.Data {
        return downloader.download(url)
    }
}
