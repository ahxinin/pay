package com.ahxinin.pay.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.ahxinin.pay.dto.CustomerAddCmd;
import com.ahxinin.pay.dto.CustomerListByNameQry;
import com.ahxinin.pay.dto.data.CustomerDTO;

public interface CustomerServiceI {

    Response addCustomer(CustomerAddCmd customerAddCmd);

    MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry);
}
