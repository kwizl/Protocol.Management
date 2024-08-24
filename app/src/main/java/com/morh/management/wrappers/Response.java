package com.morh.management.wrappers;

import java.util.List;

public class Response<T> extends BaseResponse
{
    public Response()
    { }

    public Response(List<T> data)
    {
        Succeeded = true;
        Message = "";
        Errors = null;
        Data = data;
    }

    public List<T> Data;
}
