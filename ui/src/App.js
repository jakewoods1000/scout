import React, {Component} from 'react';
import './App.css';
import ExercisesTable from "./components/ExercisesTable";
import CreateExerciseForm from "./components/CreateExerciseForm";

class App extends Component {
    render() {
        return (
            <div className="App">
                <div className="row mrgbtm">
                    <CreateExerciseForm></CreateExerciseForm>
                </div>
                <div className="row mrgnbtm">
                    <ExercisesTable></ExercisesTable>
                </div>
            </div>
        );
    }
}

export default App;