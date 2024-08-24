package com.morh.management.wrappers;

import android.net.Uri;

import java.util.List;

public class PagedResponse<T> extends Response<T>
{
    public int PagedNumber;

    public int PageSize;

    public Uri FirstPage;

    public Uri LastPage;

    public int TotalPages;

    public int TotalRecords;

    public Uri NextPage;

    public Uri PreviousPage;

    public PagedResponse() { }

    public PagedResponse(List<T> data, int pageNumber, int pageSize)
    {
        PagedNumber = pageNumber;
        PageSize = pageSize;
        Data = data;
        Message = null;
        Succeeded = true;
        Errors = null;
    }
}
