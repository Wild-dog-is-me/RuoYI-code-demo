package org.dog.common.feign;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Odin
 * @Date: 2023/6/19 11:36
 * @Description:
 */

@LocalTCC
public interface AccountServiceApi {

    /**
     * 这是一阶段操作
     * 这个方法用来检查资源，例如检查账户是否存在，检查账户余额是否充足，余额充足的话，就冻结余额
     * <p>
     * prepare 是开发者手动调用的，commit 或者 rollback 则是 seata 根据（所有的） prepare 执行的情况，自动调用的。
     */
    @TwoPhaseBusinessAction(name = "accountServiceApi", commitMethod = "commit", rollbackMethod = "rollback")
    @RequestMapping("/account/deduct/prepare")
    boolean prepare(@RequestBody BusinessActionContext actionContext, @RequestParam("userId") @BusinessActionContextParameter(paramName = "userId") String userId, @RequestParam("money") @BusinessActionContextParameter(paramName = "money") Double money);


    /**
     * 这是二阶段的提交
     * 真正的提交操作在这里完成，主要是扣款操作（操作冻结的金额即可）
     */
    @RequestMapping("/account/deduct/commit")
    boolean commit(@RequestBody BusinessActionContext actionContext);

    /**
     * 这是二阶段的回滚操作
     * 回滚主要是将冻结的金额复原
     */
    @RequestMapping("/account/deduct/rollback")
    boolean rollback(@RequestBody BusinessActionContext actionContext);
}
