package instrumentsolution;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MusicStoreService {

    private List<Instrument> instruments = new ArrayList<>();
    private AtomicLong idGen = new AtomicLong();
    private ModelMapper modelMapper;

    public MusicStoreService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public InstrumentDto getInstrument(long id) {
        return modelMapper.map(getInstrumentById(id), InstrumentDto.class);
    }

    public List<InstrumentDto> getInstruments(Optional<String> brand, Optional<Integer> price) {
        Type targetListType = new TypeToken<List<InstrumentDto>>(){}.getType();
        List<Instrument> filtered = instruments.stream()
                .filter(i -> brand.isEmpty() || i.getBrand().equalsIgnoreCase(brand.get()))
                .filter(i -> price.isEmpty() || i.getPrice() == price.get())
                .collect(Collectors.toList());
        return modelMapper.map(filtered, targetListType);
    }

    private Instrument getInstrumentById(long id) {
        return instruments.stream().filter(i -> i.getId() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Can not find the instrument"));
    }

    public InstrumentDto createInstrument(CreateInstrumentCommand command) {
        Instrument instrument = new Instrument(idGen.incrementAndGet(),
                                                command.getBrand(),
                                                command.getType(),
                                                command.getPrice(),
                                                LocalDate.now());
        instruments.add(instrument);
        return modelMapper.map(instrument, InstrumentDto.class);
    }

    public void deleteAll() {
        instruments.clear();
        idGen = new AtomicLong();
    }

    public InstrumentDto updatePrice(long id, UpdatePriceCommand command) {
        Instrument result = getInstrumentById(id);
        if(result.getPrice() != command.getPrice()) {
            result.setPrice(command.getPrice());
            result.setDate(LocalDate.now());
        }
        return modelMapper.map(result, InstrumentDto.class);
    }
}
