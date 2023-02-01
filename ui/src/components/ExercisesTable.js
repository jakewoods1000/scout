import React from "react";
import {getAllExercises} from "../ExerciseService";

class ExercisesTable extends React.Component {

    state = {
        exercises: []
    }

    async componentDidMount() {
        let exercises = await getAllExercises();
        this.setState({exercises})
    }

    render() {
        return (
            <div className="container">
                <h2>Exercises</h2>
                <table>
                    <thead>
                    <tr>
                        <th>Name</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.state.exercises.map(exercise => {
                        return (
                            <tr key={exercise.id}>
                                <td>{exercise.name}</td>
                            </tr>
                        );
                    })}
                    </tbody>
                </table>
            </div>
        );
    }
}

export default ExercisesTable;