package com.davidborza.billing.converter;

import com.davidborza.billing.entity.Bill;
import com.davidborza.billing.model.dto.BillDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created on 2023. 01. 31.
 *
 * @author David
 */
@Component
@RequiredArgsConstructor
public class BillToBillDto implements Converter<Bill, BillDto> {

    /**
     * Convert Bill to BillDto.
     *
     * @param source The given Bill object.
     * @return BillDto object.
     */
    @Override
    public BillDto convert(final @NonNull Bill source) {
        final BillDto billDto = new BillDto();
        billDto.setId(source.getId());
        billDto.setPrice(source.getPrice());
        billDto.setComment(source.getComment());
        billDto.setDueDate(source.getDueDate());
        billDto.setItemName(source.getItemName());
        billDto.setBuyersName(source.getBuyersName());
        billDto.setCreatedDate(source.getCreatedDate());

        return billDto;
    }
}
