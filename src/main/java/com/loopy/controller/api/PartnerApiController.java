package com.loopy.controller.api;

import com.loopy.controller.CrudController;
import com.loopy.domain.entity.Partner;
import com.loopy.domain.network.request.PartnerApiRequest;
import com.loopy.domain.network.response.PartnerApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/partner")
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {
}
