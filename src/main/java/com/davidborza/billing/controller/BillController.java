package com.davidborza.billing.controller;

import com.davidborza.billing.entity.Bill;
import com.davidborza.billing.model.dto.BillDto;
import com.davidborza.billing.service.BillService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2023. 01. 29.
 *
 * @author David
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/bills")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BillController {
    private final BillService billService;
    private final ConversionService conversionService;

    /**
     * Get all bills.
     *
     * @return List of bills.
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_ACCOUNTANT') or hasRole('ROLE_USER')")
    public List<BillDto> getAll() {
        return billService.getAll().stream().map(user -> conversionService.convert(user, BillDto.class)).toList();
    }

    /**
     * Get bill object by ID.
     *
     * @param id The given ID.
     * @return The found Bill object.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_ACCOUNTANT') or hasRole('ROLE_USER')")
    public BillDto getById(@PathVariable("id") final Long id) {
        return conversionService.convert(billService.getById(id), BillDto.class);
    }

    /**
     * Save the given Bill.
     *
     * @param billDto The given BillDto object.
     * @return The saved Bill.
     */
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_ACCOUNTANT')")
    public BillDto save(@RequestBody final BillDto billDto)  {
        return conversionService.convert(billService.save(conversionService.convert(billDto, Bill.class)), BillDto.class);
    }

    /**
     * Delete Bill by ID.
     *
     * @param id The given ID.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_ACCOUNTANT')")
    public void delete(@PathVariable final Long id) {
        billService.deleteById(id);
    }
}
