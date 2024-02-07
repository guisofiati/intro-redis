package github.guisofiati.introredis.repositories

import github.guisofiati.introredis.entities.Employee
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface EmployeeRepository: JpaRepository<Employee, UUID> {}
