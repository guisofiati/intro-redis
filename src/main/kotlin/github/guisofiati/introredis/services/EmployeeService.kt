package github.guisofiati.introredis.services

import github.guisofiati.introredis.entities.Employee
import github.guisofiati.introredis.repositories.EmployeeRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.BeanUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID
import java.util.logging.Logger

@Service
class EmployeeService(val employeeRepository: EmployeeRepository) {

    private val log = Logger.getLogger("EmployeeService::class")

    fun findAll(): List<Employee> {
        log.info("[FIND ALL] finding all employees in database...")
        return employeeRepository.findAll()
    }

    fun findById(employeeId: String): Employee {
        val employeeUUID = UUID.fromString(employeeId);
        log.info("[FIND ONE] finding employee in database...")
        return employeeRepository.findById(employeeUUID).orElseThrow {
            EntityNotFoundException("Employee id: $employeeId not found")
        }
    }

    fun insert(employee: Employee): Employee {
        val newEmployee = Employee()
        BeanUtils.copyProperties(employee, newEmployee)
        log.info("[INSERT] saving employee in database...")
        return employeeRepository.save(newEmployee)
    }

    fun update(employeeId: String, employee: Employee): Employee {
        val employeeUUID = UUID.fromString(employeeId);
        log.info("[UPDATE] finding employee in database...")
        val employeeEntity = employeeRepository.findById(employeeUUID).orElseThrow {
            EntityNotFoundException("Employee id: $employeeId not found")
        }
        employee.name?.let { employeeEntity.name = it }
        employee.age?.let { employeeEntity.age = it }
        employee.email?.let { employeeEntity.email = it }
        employee.phone?.let { employeeEntity.phone = it}
        log.info("[UPDATE] updating employee in database...")
        return employeeRepository.save(employeeEntity)
    }

    fun delete(employeeId: String): Boolean {
        val employeeUUID = UUID.fromString(employeeId);
        employeeRepository.findById(employeeUUID).orElseThrow {
            EntityNotFoundException("Employee id: $employeeId not found")
        }
        log.info("[DELETING] deleting employee in database...")
        employeeRepository.deleteById(employeeUUID)
        return true
    }
}