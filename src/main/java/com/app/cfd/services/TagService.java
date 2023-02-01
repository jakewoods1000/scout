package com.app.cfd.services;

import com.app.cfd.controllers.model.TagInput;
import com.app.cfd.daos.TagDao;
import com.app.cfd.models.Tag;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TagService {
    private final TagDao tagDao;

    public TagService(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public UUID insert(Tag tag) {
        return tagDao.insert(tag.getName(), tag.getDescription(),
                tag.getType());
    }

    public Tag getTag(UUID id) {
        return tagDao.getTag(id);
    }

    public List<Tag> getAllTags() {
        return tagDao.getAllTags();
    }

    public void updateTag(Tag tag) {
        tagDao.updateTag(tag.getId(), tag.getName(),
                tag.getType(), tag.getDescription());
    }

    public void deleteById(UUID id) {
        deleteAllJoinsOnTag(id);
        tagDao.deleteById(id);
    }

    public List<UUID> tagEntity(TagInput input) {
        return tagDao.inTransaction(transactional -> {
            List<UUID> joinIds = new ArrayList<>();
            for (UUID tagId : input.getTags()) {
                switch (input.getEntityType()) {
                    case EXERCISE -> joinIds.add(transactional.tagExercise(tagId, input.getEntityToBeTagged()));
                    case SET -> joinIds.add(transactional.tagSet(tagId, input.getEntityToBeTagged()));
                    case SUPER_SET -> joinIds.add(transactional.tagSuperSet(tagId, input.getEntityToBeTagged()));
                    case WORKOUT -> joinIds.add(transactional.tagWorkout(tagId, input.getEntityToBeTagged()));
                }
            }
            return joinIds;
        });
    }

    void deleteAllJoinsOnTag(UUID tagId){
         tagDao.useTransaction(transactional -> {
            tagDao.deleteJoinsFromSuperSetsByTagId(tagId);
            tagDao.deleteJoinsFromSetsByTagId(tagId);
            tagDao.deleteJoinsFromExercisesByTagId(tagId);
            tagDao.deleteJoinsFromWorkoutsByTagId(tagId);
        });
    }
}
