package com.loopy.service;

import com.loopy.ifs.CrudInterface;
import com.loopy.model.entity.Item;
import com.loopy.model.entity.User;
import com.loopy.model.network.Header;
import com.loopy.model.network.request.ItemApiRequest;
import com.loopy.model.network.response.ItemApiResponse;
import com.loopy.model.network.response.UserApiResponse;
import com.loopy.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {

        ItemApiRequest itemApiRequest = request.getData();

        Item item = Item.builder()
                .status(itemApiRequest.getStatus())
                .name(itemApiRequest.getName())
                .title(itemApiRequest.getTitle())
                .brandName(itemApiRequest.getBrandName())
                .price(itemApiRequest.getPrice())
                .content(itemApiRequest.getContent())
                .registeredAt(LocalDateTime.now())
                .build();

        Item newItem = itemRepository.save(item);

        return response(newItem);
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

    private Header<ItemApiResponse> response(Item item){
        // item -> itemApiResponse

        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getStatus())
                .brandName(item.getBrandName())
                .price(item.getPrice())
                .content(item.getContent())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .build();

        return Header.OK(itemApiResponse);
    }
}
