package com.mynt.api.controller;

import java.util.Optional;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mynt.api.model.Parcel;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {
	/**
     * Calculate cost by giving parcel dimensions.
	 * Request Payload (For Example):
	 * {
	 *		"weight": 50.00,
	 *		"height": 50.00,
	 *		"width": 50.00,
	 *		"length": 50.00
	 *	}
     *
     * @return JSON Response:
	 * {
	 *		"cost": 1000.0,
	 *		"parcelType": "HEAVY"
	 *	}
	 *
     */
	@PostMapping("/calculate")
	public ResponseEntity<String> calculateDeliveryCost(
			@RequestBody Parcel parcel,
			@RequestParam Optional<String> voucherCode) {

		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(createJsonResponse(parcel));
	}

	private String createJsonResponse(Parcel parcel) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode jsonNode = objectMapper.createObjectNode();

			jsonNode.put("cost", parcel.calculateDeliveryCost());
			jsonNode.put("parcelType", parcel.getParcelType().name());

			return objectMapper.writeValueAsString(jsonNode);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
