insert into exercises (id, name, description)
VALUES ('dbf4296b-5475-4f98-b627-60c478c6d757', 'Deadlift', 'Do a Deadlift');
insert into exercises (id, name, description)
VALUES ('ccb51070-d326-4a2b-a140-b3239b1a0a97', 'Bench Press', 'Do a bench press');
insert into exercises (id, name, description)
VALUES ('ee619d6d-66d7-453e-a9c8-b23d8eee762a', 'Front Squat', 'Do a front squat');
insert into exercises (id, name, description)
VALUES ('0acec5b9-090c-49f0-b66f-0c66a6e9db75', 'Push Ups', 'Do Push ups');
insert into exercises (id, name, description)
VALUES ('73898404-7d9d-4599-b55f-aee12356b824', 'Plank', 'Normal position plank');

----------------------------------------------------------------------
----------------------------- SETS -----------------------------------
----------------------------------------------------------------------
insert into sets (id, name, description, exercise_id, style, quantity)
VALUES ('4ed25f59-b89c-414b-839e-db60f264b3cb', 'Set One', 'Do deadlift for reps',
        'dbf4296b-5475-4f98-b627-60c478c6d757',
        'Three second negatives', '{
    "type": "REPS",
    "quantity": 10
  }');

insert into sets (id, name, description, exercise_id, style, quantity)
VALUES ('497018ea-f6d7-4633-befe-eafbc56f32d7', 'Set Two', 'Do bench press for reps',
        'ccb51070-d326-4a2b-a140-b3239b1a0a97',
        'Three second negatives', '{
    "type": "REPS",
    "quantity": 10
  }');

insert into sets (id, name, description, exercise_id, style, quantity)
VALUES ('00839a84-cf17-48e9-9a30-e3ac512f6cda', 'Set Three', 'Do front squat for reps',
        'ee619d6d-66d7-453e-a9c8-b23d8eee762a',
        'Three second negatives', '{
    "type": "REPS",
    "quantity": 10
  }');

insert into sets (id, name, description, exercise_id, style, quantity)
VALUES ('228310f4-d019-401c-b36e-d819c5ed0417', 'Set Four', 'Do Push Ups for reps',
        '0acec5b9-090c-49f0-b66f-0c66a6e9db75',
        'Three second negatives', '{
    "type": "REPS",
    "quantity": 10
  }');

insert into sets (id, name, description, exercise_id, style, quantity)
VALUES ('dc273147-00ea-4622-812a-f802af46e18f', 'Set Four', 'Do Push Ups for reps',
        '73898404-7d9d-4599-b55f-aee12356b824',
        'NONE', '{
    "type": "TIMED",
    "quantity": {
      "number": 30,
      "unit": "Seconds"
    }
  }');

----------------------------------------------------------------------
----------------------- SUPER SETS -----------------------------------
----------------------------------------------------------------------

insert into super_sets (id, name, description, reps)
VALUES ('c5424b1c-62c9-431f-be3f-43e2c8f470a3', 'Super Set One', 'Upper Body',
        4);

insert into super_sets (id, name, description, reps)
VALUES ('a044ae57-c2e6-44f8-9a7c-989088ed3acb', 'Super Set Two', 'Lower Body',
        4);

insert into sets_super_sets_join (id, set_id, super_set_id)
VALUES ('6bcebefa-23b1-4c53-b7c8-4f2085ec23d9',
        '497018ea-f6d7-4633-befe-eafbc56f32d7',
        'c5424b1c-62c9-431f-be3f-43e2c8f470a3');

insert into sets_super_sets_join (id, set_id, super_set_id)
VALUES ('326042a3-a812-47fa-830d-07fe89a974bf',
        '228310f4-d019-401c-b36e-d819c5ed0417',
        'c5424b1c-62c9-431f-be3f-43e2c8f470a3');

insert into sets_super_sets_join (id, set_id, super_set_id)
VALUES ('6f0ca06f-e820-4415-a0aa-03f7752b9297',
        'dc273147-00ea-4622-812a-f802af46e18f',
        'c5424b1c-62c9-431f-be3f-43e2c8f470a3');

insert into sets_super_sets_join (id, set_id, super_set_id)
VALUES ('bebcf5dc-6379-4765-a885-e17a3d0bf030',
        '4ed25f59-b89c-414b-839e-db60f264b3cb',
        'a044ae57-c2e6-44f8-9a7c-989088ed3acb');

insert into sets_super_sets_join (id, set_id, super_set_id)
VALUES ('f49fbcdd-ae55-4c59-9dfa-a73672d20404',
        '00839a84-cf17-48e9-9a30-e3ac512f6cda',
        'a044ae57-c2e6-44f8-9a7c-989088ed3acb');

----------------------------------------------------------------------
--------------------------- WORKOUTS ---------------------------------
----------------------------------------------------------------------

insert into workouts (id, name, description)
VALUES ('d0696403-2145-43e4-bdb1-b95f97cbbd34',
        'Monday',
        'Full Body Workout');

insert into super_sets_workouts_join (id, super_set_id, workout_id)
VALUES ('3dc1da8e-4dd3-4b89-9f32-90df7af247a6',
        'c5424b1c-62c9-431f-be3f-43e2c8f470a3',
        'd0696403-2145-43e4-bdb1-b95f97cbbd34');

insert into super_sets_workouts_join (id, super_set_id, workout_id)
VALUES ('59192577-217b-446e-b067-be25cd6eab09',
        'a044ae57-c2e6-44f8-9a7c-989088ed3acb',
        'd0696403-2145-43e4-bdb1-b95f97cbbd34');



----------------------------------------------------------------------
------------------------------ TAGS ----------------------------------
----------------------------------------------------------------------

insert into tags (id, name, description, type)
VALUES ('1fb89410-6d82-45f8-b21b-cfd2fedf434f',
        'Upper Body',
        'Tag that means upper body',
        'MUSCLE_GROUP');

insert into tags (id, name, description, type)
VALUES ('3518ac46-9a79-4712-9348-3f3931594acc',
        'LOWER Body',
        'Tag that means lower body',
        'MUSCLE_GROUP');

insert into tags (id, name, description, type)
VALUES ('0791210c-39e0-4788-b237-2f7552ce5e72',
        '1/2 ORM',
        'Do at 1/2 your one rep max',
        'STYLE');

insert into workouts_tags_join (id, workout_id, tag_id)
VALUES ('897a1636-7d18-4834-9e63-eeb23b3d85b2', 'd0696403-2145-43e4-bdb1-b95f97cbbd34',
        '1fb89410-6d82-45f8-b21b-cfd2fedf434f');

insert into workouts_tags_join (id, workout_id, tag_id)
VALUES ('6b6516eb-34ce-41a7-a12b-233af94b5e78', 'd0696403-2145-43e4-bdb1-b95f97cbbd34',
        '3518ac46-9a79-4712-9348-3f3931594acc');

insert into exercises_tags_join (id, exercise_id, tag_id)
VALUES ('bc945b2b-3e3a-462a-a5f9-85203e147c82', 'ee619d6d-66d7-453e-a9c8-b23d8eee762a',
        '3518ac46-9a79-4712-9348-3f3931594acc');