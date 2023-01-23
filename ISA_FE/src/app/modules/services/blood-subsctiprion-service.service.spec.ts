import { TestBed } from '@angular/core/testing';

import { BloodSubsctiprionServiceService } from './blood-subsctiprion-service.service';

describe('BloodSubsctiprionServiceService', () => {
  let service: BloodSubsctiprionServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BloodSubsctiprionServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
