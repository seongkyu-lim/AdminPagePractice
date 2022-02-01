package com.loopy.service;

import com.loopy.ifs.CrudInterface;
import com.loopy.model.entity.Item;
import com.loopy.model.network.Header;
import com.loopy.model.network.request.ItemApiRequest;
import com.loopy.model.network.response.ItemApiResponse;
import com.loopy.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    private final ItemRepository itemRepository;

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
        Optional<Item> optional = itemRepository.findById(id);
        return optional
                .map(item -> response(item))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();
        Optional<Item> optional = itemRepository.findById(itemApiRequest.getId());

        return optional.map(item -> {
            item
                .setStatus(itemApiRequest.getStatus())
                    .setName(itemApiRequest.getName())
                    .setTitle(itemApiRequest.getTitle())
                    .setBrandName(itemApiRequest.getBrandName())
                    .setPrice(itemApiRequest.getPrice())
                    .setContent(itemApiRequest.getContent());

            itemRepository.save(item);

            return response(item);

        }).orElseGet(()-> Header.ERROR("데이터 없음."));
    }

    @Override
    public Header delete(Long id) {

        Optional<Item> optional = itemRepository.findById(id);

        return optional.map(item -> {
            itemRepository.delete(item);
            return Header.OK();
        }).orElseGet(()-> Header.ERROR("데이터 없음."));
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
