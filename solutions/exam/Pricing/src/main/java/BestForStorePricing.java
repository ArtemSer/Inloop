import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class BestForStorePricing extends ComplexPricing{
    public BestForStorePricing(ISalePricing pricing) {
        Objects.requireNonNull(pricing);
        this.add(pricing);
    }

    @Override
    public long getTotal(Sale sale) {
        AtomicLong currentTotal = new AtomicLong();
        this.getPricings().stream()
                .filter(pricing -> pricing.getTotal(sale) > currentTotal.get())
                .forEach(pricing -> currentTotal.set(pricing.getTotal(sale)));
        return currentTotal.get();
    }
}
