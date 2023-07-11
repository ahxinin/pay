package com.ahxinin.pay.customer;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.ahxinin.pay.api.CustomerServiceI;
import com.ahxinin.pay.dto.CustomerAddCmd;
import com.ahxinin.pay.dto.CustomerListByNameQry;
import com.ahxinin.pay.dto.data.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahxinin.pay.customer.executor.CustomerAddCmdExe;
import com.ahxinin.pay.customer.executor.query.CustomerListByNameQryExe;

import javax.annotation.Resource;

@Service
@CatchAndLog
public class CustomerServiceImpl implements CustomerServiceI {

    @Resource
    private CustomerAddCmdExe customerAddCmdExe;

    @Resource
    private CustomerListByNameQryExe customerListByNameQryExe;

    public Response addCustomer(CustomerAddCmd customerAddCmd) {
        return customerAddCmdExe.execute(customerAddCmd);
    }

    @Override
    public MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry) {
        return customerListByNameQryExe.execute(customerListByNameQry);
    }

}