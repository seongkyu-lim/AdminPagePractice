package com.loopy.service;

import com.loopy.domain.entity.Item;
import com.loopy.domain.network.Header;
import com.loopy.domain.network.request.ItemApiRequest;
import com.loopy.domain.network.response.ItemApiResponse;
import com.loopy.domain.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ItemApiLogicService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {

     private final PartnerRepository partnerRepository;

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
                .partner(partnerRepository.getOne(itemApiRequest.getParterId()))
                .build();

        Item newItem = baseRepository.save(item);

        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        Optional<Item> optional = baseRepository.findById(id);
        return optional
                .map(item -> response(item))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();
        Optional<Item> optional = baseRepository.findById(itemApiRequest.getId());

        return optional.map(item -> {
            item
                .setStatus(itemApiRequest.getStatus())
                    .setName(itemApiRequest.getName())
                    .setTitle(itemApiRequest.getTitle())
                    .setBrandName(itemApiRequest.getBrandName())
                    .setPrice(itemApiRequest.getPrice())
                    .setContent(itemApiRequest.getContent())
                    .setRegisteredAt(itemApiRequest.getRegisteredAt())
                    .setUnregisteredAt(itemApiRequest.getUnregisteredAt());

            baseRepository.save(item);

            return response(item);

        }).orElseGet(()-> Header.ERROR("데이터 없음."));
    }

    @Override
    public Header delete(Long id) {

        Optional<Item> optional = baseRepository.findById(id);

        return optional.map(item -> {
            baseRepository.delete(item);
            return Header.OK();
        }).orElseGet(()-> Header.ERROR("데이터 없음."));
    }


    public Header<ItemApiResponse> response(Item item){
        // item -> itemApiResponse

        //아래와 같이 세부적으로 enum 값의 내부 요소들에 대해 접근가능.
        String statusTitle = item.getStatus().getDescription();

        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .brandName(item.getBrandName())
                .price(item.getPrice())
                .content(item.getContent())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .parterId(item.getPartner().getId())
                .build();

        return Header.OK(itemApiResponse);
    }
}
