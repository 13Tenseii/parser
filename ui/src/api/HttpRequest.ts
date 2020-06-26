
export default class HttpRequest {

    constructor(
        public url: string,
        public method?: "GET" | "POST" | "PUT" | "DELETE",
        public content?: any,
        public headers?: any
    ) {}
}