package com.loopy.controller.api;

import com.loopy.ifs.CrudInterface;
import com.loopy.model.network.Header;
import com.loopy.model.network.request.ItemApiRequest;
import com.loopy.model.network.response.ItemApiResponse;
import com.loopy.service.ItemApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/item")
@RestController
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        return itemApiLogicService.create(request);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }
}
