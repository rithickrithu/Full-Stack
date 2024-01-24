import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useNavigate, useParams } from 'react-router-dom';

const EditUser = () => {
  let navigate = useNavigate();
  const { id } = useParams();

  const [user, setUser] = useState({
    name: '',
    age: 0,
    phoneNo: '',
    city: '',
    employeeDetails: {
      department: '',
      position: '',
      salary: 0,
    },
    createdTime: '',
    updatedTime: null,
  });

  const { name, age, phoneNo, city, employeeDetails, createdTime, updatedTime } = user;

  const onInputChange = (e) => {
    const { name, value } = e.target;

    setUser((prevUser) => ({
      ...prevUser,
      [name]: value,
      employeeDetails: {
        ...prevUser.employeeDetails,
        [name]: value,
      },
    }));
  };

  useEffect(() => {
    loadUser();
  }, []);

  const onSubmit = async (e) => {
    e.preventDefault();

    const userWithTime = {
      employee1: {
        name,
        age,
        phoneNo,
        city,
      },
      EmployeeDetails: {
        ...employeeDetails,
      },
      createdTime,
      updatedTime: new Date().toISOString(),
    };

    try {
      await axios.put(`http://ec2-54-253-49-79.ap-southeast-2.compute.amazonaws.com/api/update/${id}`, userWithTime);
      navigate('/');
    } catch (error) {
      console.error('Error updating user:', error);
    }
  };

  const loadUser = async () => {
    try {
      const result = await axios.get(`http://ec2-54-253-49-79.ap-southeast-2.compute.amazonaws.com/api/get/${id}`);
      const userFromApi = result.data[0];

      setUser({
        name: userFromApi.name,
        age: userFromApi.age,
        phoneNo: userFromApi.phoneNo,
        city: userFromApi.city,
        employeeDetails: {
          department: userFromApi.employeeDetails.department,
          position: userFromApi.employeeDetails.position,
          salary: userFromApi.employeeDetails.salary,
        },
        createdTime: userFromApi.createdTime,
        updatedTime: userFromApi.updatedTime,
      });
    } catch (error) {
      console.error('Error loading user:', error);
    }
  };

  return (
    <div className='container'>
      <div className='row'>
        <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
          <h2 className='text-center m-4'>Edit User</h2>

          <form autoComplete='on' onSubmit={(e) => onSubmit(e)}>
            <div className='mb-3'>
              <label htmlFor='name' className='form-label'>
                Name
              </label>
              <input
                type='text'
                className='form-control'
                placeholder='Enter your name'
                id='name'
                name='name'
                value={name}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className='mb-3'>
              <label htmlFor='age' className='form-label'>
                Age
              </label>
              <input
                type='number'
                className='form-control'
                placeholder='Enter your age'
                id='age'
                name='age'
                value={age}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className='mb-3'>
              <label htmlFor='phoneNo' className='form-label'>
                Phone Number
              </label>
              <input
                type='text'
                className='form-control'
                placeholder='Enter your phone number'
                id='phoneNo'
                name='phoneNo'
                value={phoneNo}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className='mb-3'>
              <label htmlFor='city' className='form-label'>
                City
              </label>
              <input
                type='text'
                className='form-control'
                placeholder='Enter your city'
                id='city'
                name='city'
                value={city}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className='mb-3'>
              <label htmlFor='department' className='form-label'>
                Department
              </label>
              <input
                type='text'
                className='form-control'
                placeholder='Enter your department'
                id='department'
                name='department'
                value={employeeDetails.department}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className='mb-3'>
              <label htmlFor='position' className='form-label'>
                Position
              </label>
              <input
                type='text'
                className='form-control'
                placeholder='Enter your position'
                id='position'
                name='position'
                value={employeeDetails.position}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className='mb-3'>
              <label htmlFor='salary' className='form-label'>
                Salary
              </label>
              <input
                type='number'
                className='form-control'
                placeholder='Enter your salary'
                id='salary'
                name='salary'
                value={employeeDetails.salary}
                onChange={(e) => onInputChange(e)}
              />
            </div>

            <div className='text-center'>
              <button type='submit' className='btn btn-outline-primary'>
                Update
              </button>
              <Link className='btn btn-outline-danger mx-2' to='/'>
                Cancel
              </Link>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default EditUser;
