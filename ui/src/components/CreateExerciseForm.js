import React from "react";

class CreateExerciseForm extends React.Component {

    state = {
        exercise: {}
    }

    onChangeForm = (e) => {
        let exercise = this.state.exercise
        if (e.target.name === 'name') {
            exercise.name = e.target.value;
        }
        this.setState({exercise})
    }

    render() {
        return (
            <div className="container">
                <h2>Exercise</h2>
            </div>
        );
    }
}

export default CreateExerciseForm;