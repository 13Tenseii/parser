export default interface HttpResponse<T> {
    data: T,
    status: number,
    statusText: string,
    headers: Object,
    message?: string;
}