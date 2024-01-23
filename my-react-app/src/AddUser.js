import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function AddUser() {
  let navigate = useNavigate();

  const [user, setUser] = useState({
    employee1: {
      name: "",
      age: "",
      phoneNo: "",
      city: "",
    },
    EmployeeDetails: {
      department: "",
      position: "",
      salary: "",
    },
    createdTime: "", 
  });

  const { employee1, EmployeeDetails } = user;

  const onInputChange = (e) => {
    const { name, value } = e.target;

    setUser((prevUser) => ({
      ...prevUser,
      [name]: {
        ...prevUser[name],
        [e.target.id]: value,
      },
    }));
  };

  const onSubmit = async (e) => {
    e.preventDefault();

    // Update the state with the current time for createdTime
    setUser((prevUser) => ({
      ...prevUser,
      createdTime: new Date().toISOString(),
    }));

    try {
      await axios.post("http://localhost/api/save", user);
      navigate("/");
    } catch (error) {
      console.error("Error adding user:", error);
    }
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Register User</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="name" className="form-label">
                Name
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter your name"
                id="name"
                name="employee1"
                value={employee1 ? employee1.name : ""}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="age" className="form-label">
                Age
              </label>
              <input
                type="number"
                className="form-control"
                placeholder="Enter your age"
                id="age"
                name="employee1"
                value={employee1 ? employee1.age : ""}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="phoneNo" className="form-label">
                Phone Number
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter your phone number"
                id="phoneNo"
                name="employee1"
                value={employee1 ? employee1.phoneNo : ""}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="city" className="form-label">
                City
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter your city"
                id="city"
                name="employee1"
                value={employee1 ? employee1.city : ""}
                onChange={(e) => onInputChange(e)}
              />
            </div>

            <div className="mb-3">
              <label htmlFor="department" className="form-label">
                Department
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter your department"
                id="department"
                name="EmployeeDetails"
                value={EmployeeDetails ? EmployeeDetails.department : ""}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="position" className="form-label">
                Position
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter your position"
                id="position"
                name="EmployeeDetails"
                value={EmployeeDetails ? EmployeeDetails.position : ""}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="salary" className="form-label">
                Salary
              </label>
              <input
                type="number"
                className="form-control"
                placeholder="Enter your salary"
                id="salary"
                name="EmployeeDetails"
                value={EmployeeDetails ? EmployeeDetails.salary : ""}
                onChange={(e) => onInputChange(e)}
              />
            </div>

            <div className="text-center">
              <button type="submit" className="btn btn-outline-primary">
                Submit
              </button>
              <Link className="btn btn-outline-danger mx-2" to="/">
                Cancel
              </Link>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
