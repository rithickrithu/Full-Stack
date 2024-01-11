import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';

export default function Home() {
    const [employees, setEmployees] = useState([]);
    const { id } = useParams();

    useEffect(() => {
        loadEmployees();
    }, []);

    const loadEmployees = async () => {
        try {
            const result = await axios.get("http://localhost:8081/api/get-all");
            setEmployees(result.data);
        } catch (error) {
            console.error("Error loading employees:", error);
        }
    };

    const deleteEmployee = async (id) => {
        await axios.delete(`http://localhost:8081/api/delete/${id}`);
        loadEmployees();
    };

    return (
        <div className="container-fluid">
            <div className='py-4'></div>
            <table className="table border shadow">
                <thead>
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Age</th>
                        <th scope="col">PhoneNo</th>
                        <th scope="col">City</th>
                        <th scope="col">Department</th>
                        <th scope="col">Position</th>
                        <th scope="col">Salary</th>
                        <th scope="col">Created Time</th>
                        <th scope="col">Updated Time</th>
                        <th scope='col' className='text-center'>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {employees.map((employee) => (
                        <tr key={employee.id}>
                            <th scope="row">{employee.id}</th>
                            <td>{employee.name}</td>
                            <td>{employee.age}</td>
                            <td>{employee.phoneNo}</td>
                            <td>{employee.city}</td>
                            <td>{employee.employeeDetails.department}</td>
                            <td>{employee.employeeDetails.position}</td>
                            <td>{employee.employeeDetails.salary}</td>
                            <td>{employee.createdTime}</td>
                            <td>{employee.updatedTime}</td>
                            <td>
                                <div className='d-flex justify-content-between'>
                                    <Link className='btn btn-primary' to={`/viewuser/${employee.id}`}>View</Link>
                                    
                                    <Link className='btn btn-outline-primary' to={`/edituser/${employee.id}`}>Edit</Link>
                                    
                                    <button className='btn btn-danger' onClick={() => deleteEmployee(employee.id)}>Delete</button>
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
