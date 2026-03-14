import React, { useState } from 'react';

function StudentManager() {
  // Initial students array with 5 student objects
  const initialStudents = [
    { id: 1, name: "Rahul", course: "CSE" },
    { id: 2, name: "Priya", course: "ECE" },
    { id: 3, name: "Amit", course: "MECH" },
    { id: 4, name: "Sneha", course: "IT" },
    { id: 5, name: "Vikram", course: "CIVIL" }
  ];

  // useState to store the students array
  const [students, setStudents] = useState(initialStudents);

  // useState to store the new student input fields
  const [newStudent, setNewStudent] = useState({ id: "", name: "", course: "" });

  // Handle input change using onChange event
  const handleChange = (e) => {
    const { name, value } = e.target;
    setNewStudent({ ...newStudent, [name]: value });
  };

  // Add student to the array
  const addStudent = () => {
    if (newStudent.id && newStudent.name && newStudent.course) {
      setStudents([...students, { ...newStudent, id: Number(newStudent.id) }]);
      setNewStudent({ id: "", name: "", course: "" }); // Clear input fields
    } else {
      alert("Please fill all fields");
    }
  };

  // Delete student from the array
  const deleteStudent = (id) => {
    setStudents(students.filter((student) => student.id !== id));
  };

  return (
    <div className="student-manager">
      <h2>Add New Student</h2>

      <div className="input-group">
        <input
          type="number"
          name="id"
          placeholder="Enter ID"
          value={newStudent.id}
          onChange={handleChange}
        />
        <input
          type="text"
          name="name"
          placeholder="Enter Name"
          value={newStudent.name}
          onChange={handleChange}
        />
        <input
          type="text"
          name="course"
          placeholder="Enter Course"
          value={newStudent.course}
          onChange={handleChange}
        />
        <button className="add-btn" onClick={addStudent}>Add Student</button>
      </div>

      <h2>Student List</h2>

      {students.length === 0 ? (
        <p className="empty-message">No students available</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Course</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {students.map((student) => (
              <tr key={student.id}>
                <td>{student.id}</td>
                <td>{student.name}</td>
                <td>{student.course}</td>
                <td>
                  <button className="delete-btn" onClick={() => deleteStudent(student.id)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default StudentManager;
