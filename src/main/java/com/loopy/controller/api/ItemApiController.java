package com.loopy.controller.api;

import com.loopy.controller.CrudController;
import com.loopy.domain.entity.Item;
import com.loopy.domain.network.request.ItemApiRequest;
import com.loopy.domain.network.response.ItemApiResponse;
import com.loopy.service.ItemApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RequestMapping("/api/item")
@RestController
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {

}
