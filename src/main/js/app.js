
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');


class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {students: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/students'}).done(response => {
			this.setState({students: response.entity._embedded.students});
		});
	}

	render() {
		return (
			<StudentList students={this.state.students}/>
		)
	}
}

class StudentList extends React.Component{
	render() {
		var students = this.props.students.map(student =>
			<Student key={student._links.self.href} student={student}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
					</tr>
					{students}
				</tbody>
			</table>
		)
	}
}

class Student extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.student.surName}</td>
				<td>{this.props.student.firstName}</td>
			</tr>
		)
	}
}

ReactDOM.render(
		<App />,
		document.getElementById('react')
	)