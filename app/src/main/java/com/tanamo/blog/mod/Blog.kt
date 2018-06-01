package com.tanamo.blog.mod


class Blog {

    var title: String = ""
    var info: String = ""
    var image: String = ""
    var url: String = ""

    constructor() {

    }

    constructor(title: String, info: String, image: String, url: String) {
        this.title = title
        this.info = info
        this.image = image
        this.url = url
    }


}
