import HttpResponse from "@/api/HttpResponse";
import HttpRequest from "@/api/HttpRequest";
import axios from "axios";
import GitStatsDto from "@/api/dto/GitStatsDto";

export default class RestApi {
    private static readonly restUrl: string = window.location.origin + '/api';


    private call<T>(req: HttpRequest): Promise<HttpResponse<T>> {
        return axios(this.prepareRequest(req)).catch(resp => {
            return Promise.reject(resp);
        }) as Promise<HttpResponse<T>>;
    }

    private prepareRequest(req: HttpRequest): any {
        const targetUrl: string = req.url.startsWith('/') ? req.url : ('/' + req.url);
        const properties: any = {
            url: RestApi.restUrl + targetUrl,
            method: req.method
        };
        if (req.content != null)
            properties.data = req.content;

        if (req.headers != null)
            properties.headers = req.headers;
        return properties;
    }

    public getGitStats(login: string): Promise<HttpResponse<GitStatsDto>> {
        return this.call(new HttpRequest(
            "gitStats/" + login,
            "GET"
        ));
    }

}