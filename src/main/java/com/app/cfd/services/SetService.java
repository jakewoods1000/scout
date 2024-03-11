package com.app.cfd.services;

import com.app.cfd.daos.SetDao;
import com.app.cfd.daos.TagDao;
import com.app.cfd.models.Set;
import com.app.cfd.models.quantity.Quantity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class SetService {
    private final SetDao setDao;
    private final TagDao tagDao;
    private final ObjectMapper objectMapper;

    public SetService(SetDao setDao, TagDao tagDao, ObjectMapper objectMapper) {
        this.setDao = setDao;
        this.tagDao = tagDao;
        this.objectMapper = objectMapper;
    }

    public void updateSet(Set set) throws JsonProcessingException {
//        TODO: Add to this check if a user has admin permissions they can edit anything as well
        if (Objects.equals(set.getUserId(), setDao.findById(set.getId()).getUserId())) {
            setDao.updateSet(set, getQuantityAsString(set.getQuantity()));
        } else {
//            TODO: We may consider raising an error instead, or doing this but also raising something to the user to inform them of this action
            setDao.insert(set, getQuantityAsString(set.getQuantity()));
        }
    }

    public void deleteById(UUID id) {
        setDao.useTransaction(transactional -> {
            tagDao.deleteJoinsFromSetsBySetId(id);
            transactional.deleteById(id);
        });
    }

    public String getQuantityAsString(Quantity quantity) throws JsonProcessingException {
        return objectMapper.writeValueAsString(quantity);
    }
}
