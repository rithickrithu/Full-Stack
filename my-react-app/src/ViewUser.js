import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';

export default function ViewUser() {
    const [user, setUser] = useState({
        name: '',
        age: '',
        phoneNo: '',
        city: '',
        department: '',
        position: '',
        salary: '',
        createdTime: '',
        updatedTime: '',
    });

    const { id } = useParams();

    useEffect(() => {
        loadUser();
    }, []);

    const loadUser = async () => {
        try {
            const result = await axios.get(`http://ec2-54-253-49-79.ap-southeast-2.compute.amazonaws.com:8081/api/get/${id}`);
            
            const userFromApi = result.data[0];
            
            setUser({
                name: userFromApi.name,
                age: userFromApi.age,
                phoneNo: userFromApi.phoneNo,
                city: userFromApi.city,
                department: userFromApi.employeeDetails.department,
                position: userFromApi.employeeDetails.position,
                salary: userFromApi.employeeDetails.salary,
                createdTime: userFromApi.createdTime,
                updatedTime: userFromApi.updatedTime,
            });
        } catch (error) {
            console.error('Error loading user:', error);
        }
    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">User Details</h2>

                    <div className="card">
                        <div className="card-header">
                            Details of user id: {id}
                        </div>
                        <ul className="list-group list-group-flush">
                            <li className="list-group-item">
                                <b>Name:</b> {user.name}
                            </li>
                            <li className="list-group-item">
                                <b>Age:</b> {user.age}
                            </li>
                            <li className="list-group-item">
                                <b>Phone Number:</b> {user.phoneNo}
                            </li>
                            <li className="list-group-item">
                                <b>City:</b> {user.city}
                            </li>
                            <li className="list-group-item">
                                <b>Department:</b> {user.department}
                            </li>
                            <li className="list-group-item">
                                <b>Position:</b> {user.position}
                            </li>
                            <li className="list-group-item">
                                <b>Salary:</b> {user.salary}
                            </li>
                            <li className="list-group-item">
                                <b>Created Time:</b> {user.createdTime}
                            </li>
                            <li className="list-group-item">
                                <b>Updated Time:</b> {user.updatedTime}
                            </li>
                        </ul>
                        <div className="card-footer d-flex justify-content-center">
                            <Link className="btn btn-primary" to={'/'}>
                                Back to Home
                            </Link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
