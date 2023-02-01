export async function getAllExercises() {

    const response = await fetch('/exercises');
    let json = await response.json();
    console.log("Exercises response: " + json);
    return json;
}

export async function createExercise(data) {
    const response = await fetch(`/exercises`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    })
}

/**
 * @param id {String}
 * @returns {Promise<any>}
 */
export async function getExerciseById(id) {

    const response = await fetch('/exercises/' + id);
    return await response.json();
}
